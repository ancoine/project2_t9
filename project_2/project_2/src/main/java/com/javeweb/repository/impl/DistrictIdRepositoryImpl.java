package com.javeweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.javeweb.repository.DistrictIdRepository;
import com.javeweb.repository.entity.DistrictIDEntity;

public class DistrictIdRepositoryImpl implements DistrictIdRepository {
	static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
    static final String USER = "root";
    static final String PASS = "123456";
	@Override
	public List<DistrictIDEntity> findAll(String name,Long id) {
	    StringBuilder sql = new StringBuilder("SELECT * FROM district WHERE name LIKE ?");
	    List<DistrictIDEntity> results = new ArrayList<>();
	    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	         PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
	        
	      
	        stmt.setString(1, "%" + name + "%");

	        
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            DistrictIDEntity districtIDEntity = new DistrictIDEntity();
	            districtIDEntity.setName(rs.getString("name"));
	            results.add(districtIDEntity);
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); 
	    }
	    return results;
	}


}
