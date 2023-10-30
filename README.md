
# Java E-Commerce

A project for E-Commerce in command line using Java.


## 🛠 Technology used

 - Core Java

## Users

- Admin 
    
        *Admin can login with his/her valid credentials.

        *Here we have used one admin with email-(admin@admin.com) and password(admin).

        *Admin can able to do the following process.
        1. Add product.
        2. Delete product.
        3. Edit products.
        4. See all products.
        5. See all users.
        6. See all orders

- User 
    
        *User can login with his/her valid credentials.

        *Here user can register if he newly visits the application and can login with their credentials.
    
        *Admin can able to do the following process.
        1. See category.
        2. See products.
        3. Add the products to cart.
        4. Checkout.
        5. Placing the order.
        



## Components used

- App Component

- Admin Component
- Authentication Component
- Cart Component
- Category Component
- Home Component
- Order Component
- Product Component


## Working of components

- App component
        
        - The Application starts here by loading all the products and categories.
        - The welcome message prints and the authentication starts running.

- Authentication component

        - Login validation will be done here.
        - Registration also will be done by by utilizing the users csv file.

- Home component

        - After validation, the home page will be loaded for users, with the 5 options like,
            1. Show Category
            2. Show products
            3. Show Cart
            4. Show Orders
            5. Logout

- Admin component

        - Once admin validation done, admin home page will be shown with 6 options like,
            1. Show all products
            2. Add product 
            3. Delete product
            4. See all users
            5. See all Orders
            6. Logout

- Category component
       
        - Categories will be loaded using the category component.

- Product component

        Products will be loaded with the show product method in product component and will get the input from the user for add to cart.

- Cart component

        - Adding products to cart page will be done here for specific users with loggedInUser.

- Order component

        - After checkout, the new csv file will be created with order date and id.


## For Storage purpose,

- Users in CSV file.

- Category in ArrayList.
- Products in ArrayList.
- Cart in ArrayList.
- Orders in CSV file.





## Challanges faced:
       1. Add to Cart - While adding the products to the cart, which took a bit extra time for me.
       2. Date format - Formatting date really tooks a pity much time for me because of using it for the first time
       3. Add product from admin - While adding the new product to the existing products, after getting the old products i have missed a logic in the certain part. Later I have analyzed it and rectified.
       4. Most of the Challanges faced in logic side because of doing it for the first time.

## Todo

- Edit product from Admin side.
- Have to fix some minor bugs related to usability of code.
- Remove user if in need.
- Had to decorate the UI for better impression.


