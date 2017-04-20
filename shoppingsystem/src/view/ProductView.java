package view;

import java.util.ArrayList;
import java.util.Scanner;

import entity.Product;

public class ProductView {
	private int pId;
	private double price;
	private int num;
	private Product product;
	private String name;
	private int page;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public void lookAll(ArrayList<Product> list){
		System.out.println("编号\t商品名\t价格\t库存");
		for(Product p:list){
			System.out.println(p.getId()+"\t"+p.getName()+"\t"+p.getPrice()+"\t"+p.getNum());
		}
	}
	public void lookpage(ArrayList<Product> list){
		System.out.println("编号\t商品名\t价格\t库存");
		for(Product p:list){
			System.out.println(p.getId()+"\t"+p.getName()+"\t"+p.getPrice()+"\t"+p.getNum());
		}
	}
	public void page(){
		System.out.println("输入查看的页码");
		page=Integer.parseInt(getString());
	}
	public void pages(int pages){
		for(int i=1;i<=pages;i++){
			System.out.print(i+" ");
		}
		System.out.println("页");
	}
	public void lookById(Product p){
		System.out.println("编号\t商品名\t价格\t库存");
		System.out.println(p.getId()+"\t"+p.getName()+"\t"+p.getPrice()+"\t"+p.getNum());
	}
	public void add(){
	
		System.out.println("输入商品名");
		 name=getString();
		System.out.println("输入商品的价格");
		price= Double.parseDouble(getString());
		System.out.println("输入库存");
		num=Integer.parseInt(getString());
		product= new Product(0,name,price,num);
	}
	public void modifyPrice(){
		System.out.println("输入商品编号");
		pId=Integer.parseInt(getString());
		System.out.println("输入商品的新价格");
		price= Double.parseDouble(getString());
	}
	public void modifyNum(){
		System.out.println("输入商品编号");
		pId=Integer.parseInt(getString());
		System.out.println("输入商品的新库存");
		num=Integer.parseInt(getString());
	}
	public void delete(){
		System.out.println("商品编号");
		pId=Integer.parseInt(getString());
	}
	public void lookByName(){
		System.out.println("输入商品名");
		name=getString();
	}
	public void modifyCartNum(){
		System.out.println("输入商品名");
		name=getString();
		System.out.println("输入修改后数量");
		num=Integer.parseInt(getString());
	}
	private String getString() {
		Scanner sc= new Scanner(System.in);
		return sc.next();
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}

}
