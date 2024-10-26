# Product Management System ![](images/product-system.png)

## Objective
![](images/down-arrow.png)![](images/down-arrow.png)![](images/down-arrow.png)    [Go to see examples and explanation about Product Management System](#example-pages)  
This system consists of two Spring Boot projects: one for the backend (REST API) and the other for the frontend using JSF with PrimeFaces. The goal is to implement a full-featured product management system allowing users to perform CRUD operations on products through a user-friendly web interface.

## Project Overview ![](images/overview.png)

### 1. Backend Project (Spring Boot, Spring Data JPA, Spring REST) ![](images/backend.png)

- **Framework:** Spring Boot
- **Database Interaction:** Spring Data JPA
- **API Documentation:** OpenAPI
- **Error Handling:** @ControllerAdvice for global error handling
- **Caching:** @Cashable Implemented 
- **Logging:** SLF4J/Logback for logging
- **Build Tool:** Maven

#### Features

- **Product Entity:**
  - Fields: `id`, `name`, `description`, `price`
  - RESTful APIs for product management (Create, Read, Update, Delete)
- **Error Handling:**
  - Custom exception handling using `@ControllerAdvice`.
  - Defined global error responses.
- **OpenAPI:** Integrated for API documentation.
- **Caching:** Improved performance by caching product listings.

### 2. Frontend Project (JSF, PrimeFaces, Spring Boot) ![](images/frontend.png)

- **Framework:** JSF, PrimeFaces with Spring Boot
- **UI Components:** PrimeFaces for rich UI components
- **Backend Integration:** WebClient used to communicate with backend REST APIs
- **Build Tool:** Maven

#### Features

- **Pages:**
  - `product.xhtml`: Lists all products.
  - `error.xhtml`: Something went wrong!.
  - `error-404.xhtml`: Page Not Found.
  - `error-500.xhtml`: Internal Server Error.
  
### Example Pages

  - **Product Listing Page (`product.xhtml`): ![](images/check-list.png)**
    - Displays a table of products with options to edit or delete.
    - Implements pagination and sorting.
    - Add button to add new product
    ![](images/getAllProducts.png)
  - **Add New Product   ![](images/add-button.png)**
    - From add button at the bottom of the page we can add new product.  
    - It loaded automatically when close the add new product dialog.  
    ![](images/addProduct.png)

  - **Edit Product ![](images/edit-button.png)**
    - From edit button we can edit product.  
    - It loaded automatically when close the edit product dialog.  
    ![](images/editProduct.png)
    
- **Delete Product Confirmation ![](images/delete-button.png)**
    - Popup dialog for confirming the deletion of a product.
    - It loaded automatically when close the edit product dialog.  
    ![](images/deleteProduct.png)

  - **Exception Handling ![](images/exception-handling.png)**
    - Add GlobalException to handle the ProductService's exception.
      1. Handle the exception for delete product depends on unfound product-ID
        ![](images/product-not-found-delete.png)
      2. Handle the exceptions that are generating from Invalid input.
        ![](images/invalidate-inputs.png)
      1. Handle the exception for update product depends on unfound product-ID
        ![](images/product-not-found-update.png)
- **logging using SLF4J ![](images/log-icon.png)**
    - Using SFL44J to handle log messages.  
    ![](images/log.png)

- **Casing ![](images/cash-icon.png)**
    - Add cashing consept for our ProductService.
    - here it's not a best choice for cashing data, I used it for just testing.
    ![](images/cash.png)

- **OpenApi ![](images/documentation-api.png)**
  - Using OpenApi for auto documentation generating for our ProductService.
    ![](images/open-api.png)
    
## Future Plan ![](images/future.png)
- Add SpringSecurity using JWT for ProductService Project.
- Add singup page and approval page for ProductService users.
## Architecture ![](images/architecture.png)

The system follows a clean separation of concerns:

- **Backend:** Provides RESTful services for product management.
- **Frontend:** Handles user interactions and communicates with the backend via HTTP calls (WebClient).

## Build and Run Instructions ![](images/build-tool.png)

### Prerequisites

- ![](images/java.png) Java 17
- ![](images/build.png) Maven
- ![](images/server.png) Tomcat
- ![](images/build.png) Intillij
- ![](images/database.png) A running MySQL/PostgreSQL database for product data.
