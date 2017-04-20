drop table t_user ;
create table t_user 
(
   userId             number(10)           not null,
   uname              varchar2(20),
   upassword           varchar2(20),
   uage                number(3),
   gender             varchar2(4),
   constraint PK_T_USER primary key (userId)
);
create sequence seq_user start with 1000 increment by 1;
drop sequence seq_user;
select *from t_user;
delete from t_user where userId=1002;
SELECT U.USERID UI,U.UNAME UN,U.UPASSWORD UP,U.GENDER UG,U.UAGE UA, A.ACCOUNTID AI,A.MONEY AM,A.APASSWORD AP FROM T_USER U JOIN T_ACCOUNT A ON U.USERID=U.USERID;
SELECT USERID UI,UNAME UN,PASSWORD UP,GENDER UG,AGE UA, ACCOUNTID AI,MONEY AM,APASSWORD AP FROM T_USER JOIN T_ACCOUNT ON T_USER.USERID=T_ACCOUNT.USERID
insert into T_user values (seq_user.nextval,'haha','123456',20,'nan');
SELECT U.USERID UI,U.UNAME UN,U.UPASSWORD UP,U.GENDER UG,U.UAGE UA, A.ACCOUNTID AI,A.MONEY AM,A.APASSWORD AP FROM T_USER U LEFT JOIN T_ACCOUNT A ON U.USERID=U.USERID WHERE U.UNAME='user';
SELECT U.USERID UI,U.UNAME UN,U.UPASSWORD UP,U.GENDER UG,U.UAGE UA, A.ACCOUNTID AI,A.MONEY AM,A.APASSWORD AP FROM T_USER U JOIN T_ACCOUNT A ON U.USERID=U.USERID WHERE U.UNAME='admin';

drop table t_account ;
create table t_account 
(
   accountId          number(20)           not null,
   userId             number(10),
   money              number(10,2),
   apassword          varchar2(20),
   constraint PK_T_ACCOUNT primary key (accountId)
);
insert into t_account values(2000,1000,1000,'123456');
drop table t_account;
select *from t_account;
delete from t_account where userId=1002;

drop table t_product;
create table t_product 
(
   pid                number(10)           not null,
   pname              varchar2(20),
   price              number(10,2),
   pnum               number(10),
   constraint PK_T_PRODUCT primary key (pid)
);
insert into t_product values (seq_product.nextval,'ÁñÁ«',15,30);
select *from (select rownum rn,e.* from(select *from t_product) e)where rn between 1 and 5;
select * from (select rownum rn,p.* from(select * from t_product) p) where rn between 1 and 2;
create sequence seq_product start with  2000 increment by 1;
select *from t_product;
drop sequence  seq_product ;
SELECT PID,PNAME,PRICE,PNUM FROM T_PRODUCT ;

create table t_order 
(
   orderid            number(20)           not null,
   userId             number(10),
   total              number(20,2),
   odate              varchar2(20),
   otype              varchar2(20),
   constraint PK_T_ORDER primary key (orderid)
);
create sequence seq_order start with 3000 increment by  1;
drop table t_order;
select *from t_order;
insert into t_order values (seq_order.nextval,1001,30,'hah','wei');

create table t_orderItem 
(
   itemid             number(20)           not null,
   orderid            number(20),
   pname              varchar2(20),
   price              number(10,2),
   pnum               number(10),
   constraint PK_T_ORDERITEM primary key (itemid)
);
insert into t_orderitem values(seq_orderitem.nextval,3000,'jaj',20,10);
create sequence seq_orderItem start with 4000 increment by 1;
drop table t_orderItem;
select *from t_orderItem;
delete from t_orderItem where orderid=0;
select o.orderid  orid,o.userid uid, o.total total, o.odate odate, o.otype otype,i.itemid iid,i.pname pname,i.price price,i.pnum pnum  from  t_order o  join t_orderItem i on o.orderid =i.orderid;
select o.*,i.*  from t_order  o  join t_orderItem  i on  o.orderid=i.orderid;
select o.orderid 1,o.userid 2,o.total 3,o.odate 4,o.otype 5,i.itemid 6,i.pname 7,i.price 8,i.pnum 9 
from t_order  o  join t_orderItem  i on  o.orderid=i.orderid;
select o.*,i.*  from t_order  o  join t_orderItem  i on  o.orderid=i.orderid where o.userId=1000;


create table t_cart 
(
   cartId             number(10)           not null,
   userId             number(10),
   total              number(20,2),
   cartdate           varchar2(20),
   constraint PK_T_CART primary key (cartId)
);
create sequence seq_cart start with 5000 increment by 1;
select *from t_cart;
drop table t_cart; 


create table t_cartItem 
(
   cartItemid         number(10)           not null,
   cartId             number(10),
   pname              varchar2(20),
   price              number(10,2),
   pnum               number(10),
    constraint PK_T_CARTITEM primary key (cartItemid)
);
drop table t_cartItem;
create sequence seq_cartItem start with 6000 increment by 1;
select *from t_cartItem;
drop sequence seq_cartItem;
DELETE FROM T_CartITEM WHERE cartItemid =6002;

select c.*,i.*  from t_cart  c  join t_CartItem  i on  c.cartid=i.cartid;