package com.javeweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.util.unit.DataUnit;

import com.javeweb.repository.BuildingRepository;
import com.javeweb.repository.entity.BuildingEntity;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
    static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
    static final String USER = "root";
    static final String PASS = "123456";

    // Helper method for joining the tables based on conditions
    private void sqlJoin (Map<String, Object> params, StringBuilder join) {
        String staffId = (String) params.get("staffId");
        if (DataUnit.checkData(staffId)) {
            join.append(" JOIN assignmentbuilding ab ON b.id = ab.buildingid");
        }
        String rentAreaFrom =(String)params.get("rentAreaFrom");
        String rentAreaTo =(String)params.get("rentAreaTo");
        if(DataUnit.checkData(rentAreaFrom) && DataUnit.checkData(rentAreaTo)) {
        	join.append("JOIN rentarea rt ON b.id = rt.buildingid");
        }
        String typeCode = (String)params.get("typeCode");
        if(DataUnit.checkData("typeCode")) {
        	join.append("JOIN buildingrenttype bt ON b.id = bt.building");
        	join.append("JOIN renttype ON renttype.id = bt.renttypeid");
        }
    }
     private void sqlWhereNomal (Map<String, Object> params, StringBuilder where) {
    	 for(Map.Entry<String, Object> it : params.entrySet()) {
    		 String key = it.getKey();
    		 if(!key.equals("staffId") && !key.equals("typeCode") && !key.startsWith("rentArea") && !key.startsWith("rentArea")) {
    			 String value = it.getValue().toString();
    			 if(!NumberUnit.checkNumber(value)) {
    				 where.append("AND b."+key +"LIKE '%" +value + "%'");
    			 }
    		 }
    	 }
     }
    private void sqlWhereSpecial(Map<String,Object>params,StringBuilder where, List<String> typeCode) {
    	
    	String staffId = (String)params.get("staffId");
    	if(DataUnit.checkData(staffId)) {
    		where.append("AND ab.staffId = "+staffId);
    	}
    	String rentAreaFrom = (String)params.get("rentAreaFrom");
    	String rentAreaTo = (String)params.get("rentAreaTo");
    	if(DataUnit.checkData(rentAreaTo)) {
    		where.append("AND rt.value >="+ rentAreaTo);
    	}
    	if(DataUnit.checkData(rentAreaFrom)) {
    		where.append("AND rt.value >="+ rentAreaFrom);
    	}
    	String rentPriceFrom = (String)params.get("rentPriceFrom");
    	String rentPriceTo = (String)params.get("rentPriceTo");
    	if(DataUnit.checkData(rentPriceTo)) {
    		where.append("AND b.rentpice >="+rentAreaTo);
    	}
    	if(DataUnit.checkData(rentPriceFrom)) {
    		where.append("AND b.rentpice >="+rentAreaFrom);
    	}
    	if(typeCode != null && typeCode.size() !=0) {
    		List<String> code = new ArrayList<String>();
    		for(String it : typeCode) {
    			code.add(" ' "+it +"'");
    		}
    		where.append("AND renttype.code IN("+String.join(",",code)+")");
    	}
    }
       
	@Override
	public List<BuildingEntity> findAll(Map<String, Object> params,List<String> typeCode) {
		StringBuilder sql = new StringBuilder("SELECT b.*FROM building b");
		sqlJoin(params, sql);
		StringBuilder where = new StringBuilder("WHERE 1=1");
		sqlWhereNomal(params, where);
		sqlWhereSpecial(params, where, typeCode);
		sql.append(where).append("GROUP BY b.id");
		
		List<BuildingEntity> results = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			     Statement stm = conn.createStatement();
			     ResultSet rs = stm.executeQuery(sql.toString())) {

			   
			    while (rs.next()) {
			     BuildingEntity building = new BuildingEntity();
			     building.setName(rs.getString("name"));
			     building.setFloorarea(rs.getDouble("floorarea"));
		         building.setDistricId(rs.getLong("districId"));
		         building.setWard(rs.getString("ward"));
		         building.setStreet(rs.getString("street"));
		         building.setNumberOfBasement(rs.getString("numberofbasement"));
		         building.setAreaFrom(rs.getDouble("areaFrom"));
		         building.setAreaTo(rs.getDouble("areaTo"));
		         building.setPriceFrom(rs.getLong("priceFrom"));
		         building.setPriceTo(rs.getLong("priceTo"));
		         building.setManagername(rs.getString("managername"));
		         building.setManagerphonenumber(rs.getLong("managerphonenumber"));
		         building.setStaffId(rs.getInt("staffId"));
		         building.setTypeCode(rs.getString("typeCode"));
		         building.setRentArea(rs.getString("rentAreaString"));
		         results.add(building);
			    }

			} catch (SQLException e) {
			    e.printStackTrace();  // Xử lý ngoại lệ nếu có
			}

		return results;
	}
	public class DataUnit {

	    // Phương thức tùy chỉnh để kiểm tra chuỗi không null và không rỗng
	    public static boolean checkData(String data) {
	        return data != null && !data.trim().isEmpty();
	    }
	}
	public class NumberUnit {

	    // Phương thức kiểm tra xem chuỗi có phải là số hợp lệ không
	    public static boolean checkNumber(String data) {
	        if (data == null || data.trim().isEmpty()) {
	            return false; // Nếu chuỗi null hoặc rỗng thì không phải là số
	        }
	        
	        try {
	            // Kiểm tra xem chuỗi có phải là số nguyên hay số thập phân không
	            Double.parseDouble(data);
	            return true; // Nếu có thể chuyển đổi thành số, trả về true
	        } catch (NumberFormatException e) {
	            return false; // Nếu không thể chuyển đổi thành số, trả về false
	        }
	    }
	}
}
