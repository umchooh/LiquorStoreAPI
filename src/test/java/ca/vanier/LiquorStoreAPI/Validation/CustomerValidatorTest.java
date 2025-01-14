package ca.vanier.LiquorStoreAPI.Validation;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ca.vanier.LiquorStoreAPI.entity.Customer;
import ca.vanier.LiquorStoreAPI.validation.CustomerValidator;

class CustomerValidatorTest {

    private final CustomerValidator customerValidator = new CustomerValidator();

    @Test
    void testValidateCustomer_ValidCustomer() {
        // Setup a valid customer: FisrtName, LastName,DOB,PhoneNum,Address
        Customer validCustomer = new Customer("Max", "Lee", "2006-06-08","514-232-9868", "456 St.Laurent");

        // Validate the customer
        ResponseEntity<String> response = customerValidator.validateCustomer(validCustomer);

        // Assert that the validation returns OK with the correct message
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Customer details are valid", response.getBody());
    }

    

    @Test
    void testValidateCustomer_MissingFirstName() {
        // Setup customer with missing first name
        Customer invalidCustomer = new Customer("", "Lee", "1998-11-03","333-865-6565", "8685 49 Street");

        // Validate the customer
        ResponseEntity<String> response = customerValidator.validateCustomer(invalidCustomer);

        // Assert that the validation returns BAD_REQUEST with the correct message
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error: First Name is required", response.getBody());
    }

    @Test
    void testValidateCustomer_FirstNameNull() {
        // Setup customer with missing first name
        Customer invalidCustomer = new Customer(null, "Lee", "1998-11-03","333-865-6565", "8685 49 Street");

        // Validate the customer
        ResponseEntity<String> response = customerValidator.validateCustomer(invalidCustomer);

        // Assert that the validation returns BAD_REQUEST with the correct message
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error: First Name is required", response.getBody());
    }

    @Test
    void testValidateCustomer_MissingLastName() {
        // Setup customer with missing last name
        Customer invalidCustomer = new Customer("Max", "", "1984-10-15","1234567890", "123 Street");

        // Validate the customer
        ResponseEntity<String> response = customerValidator.validateCustomer(invalidCustomer);

        // Assert that the validation returns BAD_REQUEST with the correct message
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error: Last Name is required", response.getBody());
    }

    @Test
    void testValidateCustomer_LastNameNull() {
        // Setup customer with missing last name
        Customer invalidCustomer = new Customer("Max", null, "1984-10-15","1234567890", "123 Street");

        // Validate the customer
        ResponseEntity<String> response = customerValidator.validateCustomer(invalidCustomer);

        // Assert that the validation returns BAD_REQUEST with the correct message
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error: Last Name is required", response.getBody());
    }

    @Test
    void testValidateCustomer_MissingDateOfBirth() {
        // Setup customer with missing phone number
        Customer invalidCustomer = new Customer("Peter", "Lee", "" , "123-456-7898", "265 Street");

        // Validate the customer
        ResponseEntity<String> response = customerValidator.validateCustomer(invalidCustomer);

        // Assert that the validation returns BAD_REQUEST with the correct message
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error: Date of Birth is required", response.getBody());
    }

    @Test
    void testValidateCustomer_DateOfBirthNull() {
        // Setup customer with missing phone number
        Customer invalidCustomer = new Customer("Peter", "Lee", null , "123-456-7898", "265 Street");

        // Validate the customer
        ResponseEntity<String> response = customerValidator.validateCustomer(invalidCustomer);

        // Assert that the validation returns BAD_REQUEST with the correct message
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error: Date of Birth is required", response.getBody());
    }

    @Test
    void testValidateCustomer_MissingPhoneNumber() {
        // Setup customer with missing phone number
        Customer invalidCustomer = new Customer("Steph", "Chen", "2006-11-01", "", "333 Street");

        // Validate the customer
        ResponseEntity<String> response = customerValidator.validateCustomer(invalidCustomer);

        // Assert that the validation returns BAD_REQUEST with the correct message
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error: Phone number is required", response.getBody());
    }

    @Test
    void testValidateCustomer_PhoneNumberNull() {
        // Setup customer with missing phone number
        Customer invalidCustomer = new Customer("Steph", "Chen", "2006-11-01", null, "333 Street");

        // Validate the customer
        ResponseEntity<String> response = customerValidator.validateCustomer(invalidCustomer);

        // Assert that the validation returns BAD_REQUEST with the correct message
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error: Phone number is required", response.getBody());
    }

    @Test
    void testValidateCustomer_MissingAddress() {
        // Setup customer with missing address
        Customer invalidCustomer = new Customer("Katty", "Perry", "2004-05-01", "232-456-7890", "");

        // Validate the customer
        ResponseEntity<String> response = customerValidator.validateCustomer(invalidCustomer);

        // Assert that the validation returns BAD_REQUEST with the correct message
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error: Address is required", response.getBody());
    }

    @Test
    void testValidateCustomer_AddressNull() {
        // Setup customer with missing address
        Customer invalidCustomer = new Customer("Katty", "Perry", "2004-05-01", "232-456-7890", null);

        // Validate the customer
        ResponseEntity<String> response = customerValidator.validateCustomer(invalidCustomer);

        // Assert that the validation returns BAD_REQUEST with the correct message
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error: Address is required", response.getBody());
    }

    @Test
    void testValidateCustomer_UnderageCustomer() {
        // Setup an underage customer
        Customer underageCustomer = new Customer("David", "Dawson", "2016-12-20", "123-456-7890", "88 Street");

        // Validate the customer
        ResponseEntity<String> response = customerValidator.validateCustomer(underageCustomer);

        // Assert that the validation returns BAD_REQUEST with the correct message
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error: Customer must be at least 18 years old", response.getBody());
    }

    @Test
    void testCalculateAge() {
        int age = CustomerValidator.calculateAge("2000-12-27");
        assertEquals(24, age);  

        age = CustomerValidator.calculateAge("2010-12-20");
        assertEquals(14, age);  

        age = CustomerValidator.calculateAge("2010-12-17");
        assertEquals(14, age); 
    }
}
