# Aspire Loan Application APIs
#POSTMAN Collection
Below is the link to postman collection. Please copy all the text and import it in postman. Same is also added in a separate file in this project: PostmanCollection.md

https://api.postman.com/collections/29405127-58948f94-365d-48c7-b698-5a066e63212f?access_key=PMAT-01H94BZ948FBWTP2XVN4NXW01G


# Best Practices USED
- This application is Spring boot application build with MVC architecture.
- This is build with layered architecture and has following layers:
    - Controller
    - Service
    - Repository
    - Models
- Extended the scope of the problem statement by adding more features/Rest apis.
- This module is build with Abstraction and proper access control. Below are some of the key practices used:
    - Singleton classes(where ever required)
    - Abstracted repositories with defined interfaces.
    - Error handling and exception handling.
    - Modularized code.  
    - Defined constants where ever required.  
    - Defined request params and path variables.
    - Java doc has been added for all the public methods  
    - Clean design based on SOLID principles.
    - Usecases validations are added whereever required.

# What is not used or missing
- There is no logging used for this application.
- Unit tests are not there as the scope of the problem was increased by a lot.
  
  **Note:** i am working on unit tests as well. Will include them shortly.
- Not all the validations are included. e.g. a user with same email and name can be added twice. user_id is the only unique key.  
- Little bit of code cleanup can be done. More error handling can be added.

#Below are some of the information related to this project and about the assumptions.
* This is a Java SpringBoot application build with IntelliJ IDE.
* This application has multiple Rest apis for different entities like loan, user, payment etc.
* Postman collection for all the Rest apis is already attached. You can use that by changing the request body, request parameters and URI path variables.
* Best way to run this application is to import this project in IntelliJ IDE and click Run Application.
* All the dependencies will be automatically downloaded when we do sync dependencies in our IDE.
* This application is hosted on port 8080. URL: http://localhost:8080/

# Entities Created and Corresponding APIs
## 1. USER
- User entity has to be created in the very first place.
- There are 2 types on users: 1. Admin, 2. Customer
- Only Admin users have access to certain APIs like getAllLoansWithPendingStatus()

### APIs (please refer to postman collection for request details)
#### UserController has 3 Rest APIS
- **AddUser** - add a new user to database
- **GetUser** - get a user from database with mentioned user_id
- **GetAll Users** - It is a public api for internal use to get all the users from database.

## 2. LOAN
- A loan can be created by any of the user.
- Loan status can be changed by only Admin users
- Once the loan status is changed to "Approved" then EMIs will be created in payments table/DB.

### APIs(please refer to postman collection for request details)
#### LoanController has 5 Rest APIs
- **Add Loan** - Creates a new loan
- **Get all loans for a user** - Get all the loans for a userId
- **Get all pending loans** - Get all the loans with status pending.
- **Get loan by ID** - Fetch a loan with ID from database
- **Update loan status** - This api is used to change the status of the loan application.

## 3. Repayment
- Repayment/EMI entries are created in DB when status of the loan changes from pending to approved.
- Repayment can be done by user for an amount of greater/equal to the emi amount.

### APIs(please refer to postman collection for request details)
#### PaymentController has 3 APIs
- **GetAllEMIForLoan** - Get all the repayment/emi objects for a loan id.
- **GetEMIById** - Get a repayment/emi object by repaymentId
- **RecordPayment** - record a payment against a repaymentId

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.3/maven-plugin/reference/html/#build-image)

