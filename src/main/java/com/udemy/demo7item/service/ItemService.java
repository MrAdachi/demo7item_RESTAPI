package com.udemy.demo7item.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.udemy.demo7item.model.HelloMessage;
import com.udemy.demo7item.model.Item;
import com.udemy.demo7item.repo.ItemRepository;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	private RestTemplate restTemplate;
	
	public ItemService (RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}
	
	/*
	private List<Item> allItems = new ArrayList<> (Arrays.asList(
			new Item("ネックレス", "ジュエリ"),
			new Item("パーカー", "ファッション"),
			new Item("フェイスクリーム", "ビューティー"),
			new Item("サプリメント", "ヘルス"),
			new Item("ブルーベリー", "フード")
			));
	*/
	
	@Cacheable("getItems")
	 public List<Item> getAllItems(){
		 List<Item> allItems = new ArrayList<>();
		 
		 // cacheを使用したか判別する（使用していない場合は3秒待機）
		 try {
			 Thread.sleep(3000);
		 } catch(Exception e) {
			 e.printStackTrace();
		 }
		
		 itemRepository.findAll().forEach(allItems::add);
		 
		 return allItems;
	 }
	 
	@Cacheable(value = "getItem", key = "#p0")
	 public Optional<Item> getItem(Long itemId) {
		
		 // cacheを使用したか判別する（使用していない場合は3秒待機）
		 try {
			 Thread.sleep(3000);
		 } catch(Exception e) {
			 e.printStackTrace();
		 }
		
		 return itemRepository.findById(itemId);
	 }
	 
	@CacheEvict(value = "getItems", allEntries = true)
	 public void addItem(Item item) {
		 itemRepository.save(item);
	 }
	 
	
	@Caching(evict = {
			@CacheEvict(value = "getItem", key = "#p0"),
			@CacheEvict(value = "getItems", allEntries = true)
	})
	 public void updateItem(Long itemId, Item item) {
		 if(itemRepository.findById(itemId).get() != null) {
			 itemRepository.save(item);
		 }
	 }
	 
	@Caching(evict = {
			@CacheEvict(value = "getItem", key = "#p0"),
			@CacheEvict(value = "getItems", allEntries = true)
	})
	 public void deleteItem(Long itemId) {
		 itemRepository.deleteById(itemId);
	 }
	
	public HelloMessage getHelloResponse() {
		String URL = "http://localhost:8081/hello";
		String hello = restTemplate.getForObject(URL, String.class);
		
		HelloMessage retHello = new HelloMessage(hello);
		return retHello;
	}
}
