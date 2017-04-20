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
		System.out.println("���\t��Ʒ��\t�۸�\t���");
		for(Product p:list){
			System.out.println(p.getId()+"\t"+p.getName()+"\t"+p.getPrice()+"\t"+p.getNum());
		}
	}
	public void lookpage(ArrayList<Product> list){
		System.out.println("���\t��Ʒ��\t�۸�\t���");
		for(Product p:list){
			System.out.println(p.getId()+"\t"+p.getName()+"\t"+p.getPrice()+"\t"+p.getNum());
		}
	}
	public void page(){
		System.out.println("����鿴��ҳ��");
		page=Integer.parseInt(getString());
	}
	public void pages(int pages){
		for(int i=1;i<=pages;i++){
			System.out.print(i+" ");
		}
		System.out.println("ҳ");
	}
	public void lookById(Product p){
		System.out.println("���\t��Ʒ��\t�۸�\t���");
		System.out.println(p.getId()+"\t"+p.getName()+"\t"+p.getPrice()+"\t"+p.getNum());
	}
	public void add(){
	
		System.out.println("������Ʒ��");
		 name=getString();
		System.out.println("������Ʒ�ļ۸�");
		price= Double.parseDouble(getString());
		System.out.println("������");
		num=Integer.parseInt(getString());
		product= new Product(0,name,price,num);
	}
	public void modifyPrice(){
		System.out.println("������Ʒ���");
		pId=Integer.parseInt(getString());
		System.out.println("������Ʒ���¼۸�");
		price= Double.parseDouble(getString());
	}
	public void modifyNum(){
		System.out.println("������Ʒ���");
		pId=Integer.parseInt(getString());
		System.out.println("������Ʒ���¿��");
		num=Integer.parseInt(getString());
	}
	public void delete(){
		System.out.println("��Ʒ���");
		pId=Integer.parseInt(getString());
	}
	public void lookByName(){
		System.out.println("������Ʒ��");
		name=getString();
	}
	public void modifyCartNum(){
		System.out.println("������Ʒ��");
		name=getString();
		System.out.println("�����޸ĺ�����");
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
