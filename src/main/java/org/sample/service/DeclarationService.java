package org.sample.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.sample.entity.RawData;
import org.sample.entity.RawDataRepository;
import org.sample.utility.DataType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import utility.ReturnCode;
import utility.ReturnData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(path="/declaration")
public class DeclarationService {
	@Autowired
	private RawDataRepository repo;

	@PostMapping(path="/listType")
	public @ResponseBody HashMap listType (
	){
		return ReturnData.returnData(ReturnCode.SUCCESS, new ArrayList<DataType>(Arrays.asList(DataType.values())));
	}

	@PostMapping(path="/{app}/get/{dataType}")
	public @ResponseBody HashMap getDeclaration (
			@PathVariable String app,
			@PathVariable String dataType
	){

		//---------- DIVIDER ----------
		List<RawData> result = null;
		try{
			result = repo.findByDataType(app, dataType);
			if ( result.size() == 0 ){
				return ReturnData.returnData(ReturnCode.ERR_DATA, result);
			}
		}catch(org.springframework.dao.DataAccessException e){
			System.err.println(e.getClass());
			System.err.println(e.getLocalizedMessage());
			return ReturnData.returnData(ReturnCode.ERR_DBFAIL, e.getLocalizedMessage() );
		}

		//---------- DIVIDER ----------
		return ReturnData.returnData(ReturnCode.SUCCESS, result);
	}

	@PostMapping(path="/{app}/set/{dataType}")
	public @ResponseBody HashMap setDeclaration(
			@PathVariable String app,
			@PathVariable String dataType,
			@RequestBody RawData rawData //only data is required
	) throws JsonProcessingException {
		rawData.setApp(app);
		rawData.setDataType(dataType);
		//---------- DIVIDER ----------
		try{
			List<RawData> result = repo.findByDataType(app, dataType);
			if ( result.size() == 0 ){
				repo.save(rawData);
			}else if( result.size() == 1 ){
				rawData.setId(result.get(0).getId());
				rawData.setData(result.get(0).getData());
				rawData.setCreateTime(result.get(0).getCreateTime());
				repo.save(rawData);
				rawData = repo.findByDataType(app, dataType).get(0);
			}else{
				return ReturnData.returnData(ReturnCode.ERR_DATA, rawData);
			}
		}catch(org.springframework.dao.DataAccessException e){
			System.err.println(e.getClass());
			System.err.println(e.getLocalizedMessage());
			return ReturnData.returnData(ReturnCode.ERR_DBFAIL, e.getLocalizedMessage() );
		}

		//---------- DIVIDER ----------
		return ReturnData.returnData(ReturnCode.SUCCESS, rawData);
	}

}
