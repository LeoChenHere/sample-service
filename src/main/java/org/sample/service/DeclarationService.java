package org.sample.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.sample.entity.RawData;
import org.sample.entity.RawDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import utility.ReturnCode;
import utility.ReturnData;

import java.util.HashMap;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping(path="/declaration")
public class DeclarationService {
	@Autowired
	private RawDataRepository repo;

	@GetMapping(path="/{app}/{dataType}")
	public @ResponseBody HashMap getDeclaration (
			@PathVariable String app,
			@PathVariable String dataType
	) throws JsonProcessingException {

		//---------- DIVIDER ----------

		List<RawData> result = repo.findByDataType(app, dataType);
		if ( result.size() == 0 ){
			return ReturnData.returnData(ReturnCode.ERR_DATA, result);
		}

		//---------- DIVIDER ----------
		return ReturnData.returnData(ReturnCode.SUCCESS, result);
	}

	@PostMapping(path="/set")
	public @ResponseBody HashMap set(
			@RequestBody RawData rawData
	) throws JsonProcessingException {

		//---------- DIVIDER ----------
		repo.save(rawData);

		//---------- DIVIDER ----------
		return ReturnData.returnData(ReturnCode.SUCCESS, rawData);
	}

}
