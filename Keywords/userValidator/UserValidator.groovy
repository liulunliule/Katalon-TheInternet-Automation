package userValidator

import com.kms.katalon.core.annotation.Keyword

class UserValidator {
	
	// =============================================
	// Single VALIDATE 
	// =============================================
	
	/**
	 * Validate email format
	 * @param email - Email string to validate
	 * @return true if valid, false otherwise
	 */
	@Keyword
	def validateEmail(String email) {
		if (email == null || email.isEmpty()) {
			println("[FAIL] Email is null or empty")
			return false
		}
		
		// Check basic format
		if (!email.contains('@') || !email.contains('.')) {
			println("[FAIL] Email missing '@' or '.': ${email}")
			return false
		}
		
		// Check @ comes before last .
		int atIndex = email.indexOf('@')
		int lastDotIndex = email.lastIndexOf('.')
		
		if (atIndex > lastDotIndex) {
			println("[FAIL] Email has '.' before '@': ${email}")
			return false
		}
		
		// Check there's something before @ and after last .
		if (atIndex == 0 || lastDotIndex == email.length() - 1) {
			println("[FAIL] Email missing local part or domain: ${email}")
			return false
		}
		
		println("[PASS] Email valid: ${email}")
		return true
	}
	
	/**
	 * Validate website/domain format
	 * @param website - Website string to validate
	 * @return true if valid format, false otherwise
	 */
	@Keyword
	def validateWebsite(String website) {
		if (website == null || website.isEmpty()) {
			println("[FAIL] Website is null or empty")
			return false
		}
		
		// Must contain at least one dot
		if (!website.contains('.')) {
			println("[FAIL] Website missing '.': ${website}")
			return false
		}
		
		// Should not start or end with dot
		if (website.startsWith('.') || website.endsWith('.')) {
			println("[WARN] Website starts or ends with '.': ${website}")
		}
		
		println("[PASS] Website valid: ${website}")
		return true
	}
	
	/**
	 * Validate phone number format
	 * @param phone - Phone string to validate
	 * @return true if valid format, false otherwise
	 */
	@Keyword
	def validatePhone(String phone) {
		if (phone == null || phone.isEmpty()) {
			println("[FAIL] Phone is null or empty")
			return false
		}
		
		// Phone should contain at least some digits
		if (!phone.matches('.*\\d.*')) {
			println("[WARN] Phone contains no digits: ${phone}")
		}
		
		println("[PASS] Phone valid: ${phone}")
		return true
	}
	
	/**
	 * Validate ID is positive integer
	 * @param id - ID value to validate
	 * @return true if valid, false otherwise
	 */
	@Keyword
	def validateId(def id) {
		if (id == null) {
			println("[FAIL] ID is null")
			return false
		}
		
		// Check if it's a number
		if (!(id instanceof Number)) {
			println("[FAIL] ID is not a number: ${id} (${id.class})")
			return false
		}
		
		// Check if positive
		if (id <= 0) {
			println("[FAIL] ID must be positive: ${id}")
			return false
		}
		
		println("[PASS] ID valid: ${id}")
		return true
	}
	
	/**
	 * Validate required string field (not null, not empty)
	 * @param value - String value to validate
	 * @param fieldName - Name of field for logging
	 * @return true if valid, false otherwise
	 */
	@Keyword
	def validateRequiredString(String value, String fieldName) {
		if (value == null) {
			println("[FAIL] ${fieldName} is null")
			return false
		}
		
		if (value.isEmpty()) {
			println("[FAIL] ${fieldName} is empty")
			return false
		}
		
		println("[PASS] ${fieldName}: '${value}'")
		return true
	}
	
	/**
	 * Validate field exists in object
	 * @param obj - Object to check
	 * @param fieldName - Name of field to check
	 * @return true if exists, false otherwise
	 */
	@Keyword
	def validateFieldExists(def obj, String fieldName) {
		if (obj == null) {
			println("[FAIL] Object is null, cannot check field '${fieldName}'")
			return false
		}
		
		if (obj instanceof Map) {
			if (obj.containsKey(fieldName)) {
				println("[PASS] Field '${fieldName}' exists")
				return true
			} else {
				println("[FAIL] Field '${fieldName}' is missing")
				return false
			}
		} else {
			// Try reflection for non-Map objects
			try {
				if (obj."${fieldName}" != null) {
					println("[PASS] Field '${fieldName}' exists")
					return true
				}
			} catch (Exception e) {
				println("[FAIL] Field '${fieldName}' is missing")
				return false
			}
		}
		return false
	}
	
	/**
	 * Validate field type
	 * @param value - Value to check
	 * @param expectedType - Expected type (String, Integer, Map, List, etc.)
	 * @param fieldName - Name of field for logging
	 * @return true if matches, false otherwise
	 */
	@Keyword
	def validateFieldType(def value, Class expectedType, String fieldName) {
		if (value == null) {
			println("[FAIL] ${fieldName} is null, cannot check type")
			return false
		}
		
		if (expectedType.isInstance(value)) {
			println("[PASS] ${fieldName} is ${expectedType.simpleName}")
			return true
		} else {
			println("[FAIL] ${fieldName} expected ${expectedType.simpleName}, got ${value.class.simpleName}")
			return false
		}
	}
	
	// =============================================
	// VALIDATE USER
	// =============================================
	
	/**
	 * Validate complete User object using individual validators
	 * @param user - User object (Map)
	 * @param printDetails - Whether to print detailed logs
	 * @return true if all validations pass, false otherwise
	 */
	@Keyword
	def validateUser(def user, boolean printDetails = true) {
		
		if (printDetails) {
			println("")
			println("========================================")
			println("VALIDATING USER: ${user?.name ?: 'UNKNOWN'}")
			println("========================================")
		}
		
		boolean isValid = true
		
		// 1. Check required fields exist
		if (printDetails) println("\n--- Checking Required Fields ---")
		def requiredFields = ['id', 'name', 'username', 'email', 'phone', 'website', 'address', 'company']
		
		requiredFields.each { field ->
			if (!validateFieldExists(user, field)) {
				isValid = false
			}
		}
		
		if (!isValid) {
			if (printDetails) println("\n[ABORT] Missing required fields, skipping further validation")
			return false
		}
		
		// 2. Validate individual fields
		if (printDetails) println("\n--- Validating Individual Fields ---")
		
		if (!validateId(user.id)) isValid = false
		if (!validateRequiredString(user.name, 'name')) isValid = false
		if (!validateRequiredString(user.username, 'username')) isValid = false
		if (!validateEmail(user.email)) isValid = false
		if (!validatePhone(user.phone)) isValid = false
		if (!validateWebsite(user.website)) isValid = false
		
		// 3. Validate field types
		if (printDetails) println("\n--- Validating Field Types ---")
		
		if (!validateFieldType(user.id, Integer, 'id')) isValid = false
		if (!validateFieldType(user.name, String, 'name')) isValid = false
		if (!validateFieldType(user.username, String, 'username')) isValid = false
		if (!validateFieldType(user.email, String, 'email')) isValid = false
		if (!validateFieldType(user.phone, String, 'phone')) isValid = false
		if (!validateFieldType(user.website, String, 'website')) isValid = false
		if (!validateFieldType(user.address, Map, 'address')) isValid = false
		if (!validateFieldType(user.company, Map, 'company')) isValid = false
		
		// 4. Validate nested objects
		if (printDetails) println("\n--- Validating Address ---")
		if (!validateAddress(user.address, printDetails)) isValid = false
		
		if (printDetails) println("\n--- Validating Company ---")
		if (!validateCompany(user.company, printDetails)) isValid = false
		
		// 5. Summary
		if (printDetails) {
			println("")
			println("========================================")
			if (isValid) {
				println("VALIDATION RESULT: PASS")
			} else {
				println("VALIDATION RESULT: FAIL")
			}
			println("========================================")
		}
		
		return isValid
	}
	
	/**
	 * Validate Address object
	 */
	@Keyword
	def validateAddress(def address, boolean printDetails = true) {
		if (address == null) {
			if (printDetails) println("[FAIL] Address is null")
			return false
		}
		
		boolean isValid = true
		
		def addressFields = ['street', 'suite', 'city', 'zipcode', 'geo']
		
		addressFields.each { field ->
			if (!validateFieldExists(address, field)) {
				isValid = false
			}
		}
		
		// Validate geo if exists
		if (address.geo != null) {
			if (printDetails) println("  Checking geo...")
			
			if (!validateFieldExists(address.geo, 'lat')) isValid = false
			if (!validateFieldExists(address.geo, 'lng')) isValid = false
			
			// Check lat/lng are valid numbers
			if (address.geo.lat != null && address.geo.lng != null) {
				try {
					def lat = address.geo.lat.toString().toDouble()
					def lng = address.geo.lng.toString().toDouble()
					
					if (lat < -90 || lat > 90) {
						if (printDetails) println("  [WARN] Latitude out of range: ${lat}")
					}
					if (lng < -180 || lng > 180) {
						if (printDetails) println("  [WARN] Longitude out of range: ${lng}")
					}
				} catch (Exception e) {
					if (printDetails) println("  [FAIL] lat/lng are not valid numbers")
					isValid = false
				}
			}
		}
		
		if (printDetails) {
			if (isValid) {
				println("[PASS] Address validation passed")
			} else {
				println("[FAIL] Address validation failed")
			}
		}
		
		return isValid
	}
	
	/**
	 * Validate Company object
	 */
	@Keyword
	def validateCompany(def company, boolean printDetails = true) {
		if (company == null) {
			if (printDetails) println("[FAIL] Company is null")
			return false
		}
		
		boolean isValid = true
		
		def companyFields = ['name', 'catchPhrase', 'bs']
		
		companyFields.each { field ->
			if (!validateFieldExists(company, field)) {
				isValid = false
			}
		}
		
		if (printDetails) {
			if (isValid) {
				println("[PASS] Company validation passed")
			} else {
				println("[FAIL] Company validation failed")
			}
		}
		
		return isValid
	}
	
	// =============================================
	// HÀM VALIDATE NHIỀU USERS
	// =============================================
	
	/**
	 * Validate list of users
	 * @param users - List of user objects
	 * @param printDetails - Whether to print detailed logs
	 * @return Map with validation summary
	 */
	@Keyword
	def validateAllUsers(def users, boolean printDetails = true) {
		if (printDetails) {
			println("")
			println("########################################")
			println("VALIDATING ${users.size()} USERS")
			println("########################################")
		}
		
		int validCount = 0
		int invalidCount = 0
		def failedUsers = []
		
		users.eachWithIndex { user, index ->
			if (printDetails) {
				println("\n[User ${index + 1}/${users.size()}]")
			}
			
			if (validateUser(user, printDetails)) {
				validCount++
			} else {
				invalidCount++
				failedUsers.add(user.id ?: "unknown")
			}
		}
		
		if (printDetails) {
			println("")
			println("########################################")
			println("VALIDATION SUMMARY")
			println("########################################")
			println("Total users:    ${users.size()}")
			println("Valid users:    ${validCount}")
			println("Invalid users:  ${invalidCount}")
			if (failedUsers) {
				println("Failed IDs:     ${failedUsers}")
			}
			println("########################################")
		}
		
		return [
			total: users.size(),
			valid: validCount,
			invalid: invalidCount,
			failedIds: failedUsers
		]
	}
}