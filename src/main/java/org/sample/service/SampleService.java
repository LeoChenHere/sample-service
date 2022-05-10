package org.sample.service;

import org.sample.entity.RawData;
import org.sample.entity.RawDataRepository;
import org.sample.utility.ReturnData;
import org.sample.utility.ReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@RequestMapping(path="/sample")
public class SampleService {
	@Autowired
	private RawDataRepository repo;

	@PostMapping(path="/add")
	public @ResponseBody HashMap addData (
					@RequestParam String dataType,
					@RequestParam String jsonData
	) {
		//---------- DIVIDER ----------

		RawData entity = new RawData();
		entity.setDataType(dataType);
		entity.setJsonData(jsonData);
		repo.save(entity);

		//---------- DIVIDER ----------
//		ArrayList<HashMap> alhm = new ArrayList();
//		HashMap<String, Object> hm = new HashMap<String, Object>();
//		for( int i = 0 ; i < 3 ; i++ ){
//			hm.put("att_1:", i);
//			hm.put("att_2:", "eth");
//			alhm.add(hm);
//		}
		return ReturnData.returnData(ReturnCode.OK, jsonData);
	}

	@GetMapping(path="/showall")
	public @ResponseBody Iterable<RawData> getAllCopperPush() {
		return repo.findAll();
	}


}
