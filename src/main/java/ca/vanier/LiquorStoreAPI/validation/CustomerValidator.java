package ca.vanier.LiquorStoreAPI.validation;

import java.time.LocalDate;
import java.time.Period;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ca.vanier.LiquorStoreAPI.entity.Customer;

public class CustomerValidator {


    // Method to validate all of the fields are not empty
    // If each field are empty, it will return BAD REQUEST with error message ( which ever come first)
    // For date of birth, check if its not empty or null, then proceed to check the age is over 18 years old or not
    // if every field is valid and age over 18, http status == 200(OK), then controller will proceed to insert the customer to database
    public ResponseEntity<String> validateCustomer(Customer customer) {

        if (customer.getFirst_name() == null || customer.getFirst_name().trim().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error: First Name is required");
        }
        if (customer.getLast_name() == null || customer.getLast_name().trim().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error: Last Name is required");
        }

        if (customer.getPhone_number() == null || customer.getPhone_number().trim().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error: Phone number is required");
        }

        if (customer.getAddress() == null || customer.getAddress().trim().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error: Address is required");
        }

        // Check if age is over 18
        if (customer.getDate_of_birth() == null || customer.getDate_of_birth().trim().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error: Date of Birth is required");
        } else {
            // Calculate the customer's age and check if they are at least 18
            int currentAge = calculateAge(customer.getDate_of_birth());

            if (currentAge < 18) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Error: Customer must be at least 18 years old");
            }
        }

        // All validations passed
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Customer details are valid");
    }

    // Method to calculate age difference
    // Since DOB is in string, using split method to get the year, month and day separately
    //Using buile-in class like LocalDate and Period will eb able to determine the age difference
    public static int calculateAge(String dateOfBirth) {

        // Split the date string by the "-" separator
        String[] dateParts = dateOfBirth.trim().split("-");
        Integer year = Integer.parseInt(dateParts[0]);
        Integer month = Integer.parseInt(dateParts[1]);
        Integer day = Integer.parseInt(dateParts[2]);

        LocalDate dateOfBirthLocal = LocalDate.of(year, month, day);

        LocalDate currentDate = LocalDate.now();

        Period period = Period.between(dateOfBirthLocal, currentDate);

        return period.getYears();
    }

    // public static boolean isOver18(String dateOfBirth) throws ParseException {
    //     // Parse the dateOfBirth string into a Date object
    //     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    //     Date birthDate = dateFormat.parse(dateOfBirth);

    //     // Get the current date and subtract 18 years
    //     Calendar calendar = Calendar.getInstance();
    //     calendar.add(Calendar.YEAR, -18);
    //     Date cutoffDate = calendar.getTime();

    //     // Check if the birth date is before or on the cutoff date
    //     return !birthDate.after(cutoffDate);
    // }

    // public static void main(String[] args) {
    //     try {
    //         String dateOfBirth = "2000-12-10"; // Example input
    //         if (isOver18(dateOfBirth)) {
    //             System.out.println("The customer is over 18 years old.");
    //         } else {
    //             System.out.println("The customer is under 18 years old.");
    //         }
    //     } catch (ParseException e) {
    //         System.err.println("Invalid date format. Please use yyyy-MM-dd.");
    //     }
    // }


}
