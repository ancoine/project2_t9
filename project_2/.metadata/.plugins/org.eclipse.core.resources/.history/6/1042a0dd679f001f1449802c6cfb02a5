package com.javeweb.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.javeweb.dto.response.BuildingResponseDTO;
import com.javeweb.repository.BuildingRepository;
import com.javeweb.repository.entity.BuildingEntity;
import com.javeweb.service.BuildingService;

public class BuildingServiceImpl implements BuildingService{
    @Autowired
    private BuildingRepository buildingRepository;
	public List<BuildingResponseDTO> findAll(Map<String, Object> params, List<String> typeCode) {
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(params, typeCode);
		List<BuildingResponseDTO> result = new ArrayList<BuildingResponseDTO>();
		for(BuildingEntity it : buildingEntities) {
			BuildingResponseDTO buildingResponseDTO = new BuildingResponseDTO();
			buildingResponseDTO.setId(it.getId());
			buildingResponseDTO.setName(it.getName());
			String districtName = BuildingResponseDTO.findDistrictNameById(it.getDistricId());
	        buildingResponseDTO.setAddress(it.getStreet() + ", " + it.getWard() + ", " + districtName);
            buildingResponseDTO.setNumberOfBasement(it.getNumberOfBasement());
            buildingResponseDTO.setManagername(it.getManagername());
            buildingResponseDTO.setManagerphonenumber(it.getManagerphonenumber());
            buildingResponseDTO.setFloorarea(it.getFloorarea());
            buildingResponseDTO.setVacantArea(null);
            buildingResponseDTO.setRentalArea(it.getRentArea()); 
            buildingResponseDTO.setRentPrice(it.getRentprice());
            buildingResponseDTO.setServiceFee(it.getServicefee());
            buildingResponseDTO.setBrokerageFee(it.getBrokeragefee());
            result.add(buildingResponseDTO);
        }
                     return result;
	}

}
