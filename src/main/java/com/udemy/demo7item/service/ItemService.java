package com.udemy.demo7item.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.demo7item.model.Item;
import com.udemy.demo7item.repo.ItemRepository;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	/*
	private List<Item> allItems = new ArrayList<> (Arrays.asList(
			new Item("ネックレス", "ジュエリ"),
			new Item("パーカー", "ファッション"),
			new Item("フェイスクリーム", "ビューティー"),
			new Item("サプリメント", "ヘルス"),
			new Item("ブルーベリー", "フード")
			));
	*/
	
	 public List<Item> getAllItems(){
		 List<Item> allItems = new ArrayList<>();
		 itemRepository.findAll().forEach(allItems::add);
		 
		 return allItems;
	 }
	 
	 public Optional<Item> getItem(Long itemId) {
		 return itemRepository.findById(itemId);
	 }
	 
	 public void addItem(Item item) {
		 itemRepository.save(item);
	 }
	 
	 public void updateItem(Long itemId, Item item) {
		 if(itemRepository.findById(itemId).get() != null) {
			 itemRepository.save(item);
		 }
	 }
	 
	 public void deleteItem(Long itemId) {
		 itemRepository.deleteById(itemId);
	 }
}
