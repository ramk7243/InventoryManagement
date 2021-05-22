# InventoryManagement
Login:
  url : http://18.223.24.229:8081/auth/signin
  method : Post
  username : test@demo.com
  password : test
  
  
Get product By Category

  url : http://18.223.24.229:8081/api/v1/{Category}
  Method : Post
  header : X-Token (use token generated after login
  
Get All products

  url : http://18.223.24.229:8081/api/v1/get-all-products
  Method : Post
  header : X-Token (use token generated after login

Add-Product
url : http://18.223.24.229:8081/api/v1/
Method : Post
header : X-Token (use token generated after login
Sample Body {
    "productName" : "A135",

   "productCategory" : {
	"categoryName": "Commercial"
   },
   "units": 3,
    "description" : "A135 description"
    
}



Update Product
url : http://18.223.24.229:8081/api/v1/
Method : Put
header : X-Token (use token generated after login
Sample Body {
    "productName" : "A135",
   "productCategory" : {
	"categoryName": "Commercial"
   },
   "units": 3,
    "description" : "A135 description"
    
}



 
