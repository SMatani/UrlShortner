package com.urlshortner.shortner.repository;

import com.urlshortner.shortner.entity.UrlData;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends CrudRepository<UrlData, Integer>{

}
