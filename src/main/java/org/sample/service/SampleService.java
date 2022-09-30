package org.sample.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nonapi.io.github.classgraph.json.JSONUtils;
import org.sample.entity.Person;
import org.sample.entity.RawData;
import org.sample.entity.RawDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import utility.ReturnCode;
import utility.ReturnData;

import java.util.ArrayList;
import java.util.HashMap;

@CrossOrigin
@Controller
@RequestMapping(path="/sample")
public class SampleService {
	@Autowired
	private RawDataRepository repo;

	@PostMapping(path="/add")
	public @ResponseBody HashMap addData (
			@RequestBody String jsonInfo
	) throws JsonProcessingException {
		//---------- DIVIDER ----------

		RawData entity = (new ObjectMapper()).readValue(jsonInfo, RawData.class);
		repo.save(entity);

//		RawData entity = new RawData();
//		entity.setDataType(dataType);
//		entity.setJsonData(jsonData);
//		repo.save(entity);

		//---------- DIVIDER ----------
//		ArrayList<HashMap> alhm = new ArrayList();
//		HashMap<String, Object> hm = new HashMap<String, Object>();
//		for( int i = 0 ; i < 3 ; i++ ){
//			hm.put("att_1:", i);
//			hm.put("att_2:", "eth");
//			alhm.add(hm);
//		}

		return ReturnData.returnData(ReturnCode.SUCCESS, jsonInfo);
	}

	@PostMapping(path="/json")
	public @ResponseBody Object json(@RequestBody Person person) {
		return person;
	}

	@PostMapping(path="/jsonArrayListHashMap")
	public @ResponseBody Object jsonArrayListHashMap(@RequestBody HashMap<String, Object> alhm) {
		String name = (String) alhm.get("name");
		ArrayList<String> imgs = (ArrayList<String>) alhm.get("imgs");
		String str1 = "";
		for (String str: imgs) {
			System.out.println(str);
			str1 = str1+"参数:"+str+"\n";
		}
		return alhm;
	}

	@GetMapping(path="/showall")
	public @ResponseBody Iterable<RawData> getAllCopperPush() {
		return repo.findAll();
	}


}
