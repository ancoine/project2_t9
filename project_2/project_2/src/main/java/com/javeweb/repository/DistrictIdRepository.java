package com.javeweb.repository;
import java.util.List;

import com.javeweb.repository.entity.DistrictIDEntity;
public interface DistrictIdRepository {
List<DistrictIDEntity> findAll(String name,Long id);

}
