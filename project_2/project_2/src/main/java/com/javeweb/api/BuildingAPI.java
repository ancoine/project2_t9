package com.javeweb.api;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javeweb.dto.response.BuildingResponseDTO;
import com.javeweb.repository.BuildingRepository;
import com.javeweb.service.BuildingService;

@Controller
public class BuildingAPI {

@Autowired
private BuildingService buildingService;
@GetMapping("/api/building")
private void getBuildings(@RequestParam Map<String,Object> params,
		                  @RequestParam(name="typeCode",required = false) List<String> typeCode) {
	
	
	List<BuildingResponseDTO> buldings = BuildingService.findAll(params,typeCode);
	return;
}

}
