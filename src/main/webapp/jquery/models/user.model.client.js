function User(username, password, firstName, lastName, email, phone, role, dateOfBirth) {
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phone = phone;
    this.role = role;
    this.dateOfBirth = dateOfBirth;

    self = this;

    this.getUsername = getUsername;
    this.setUsername = setUsername;
    this.getPassword = getPassword;
    this.setPassword = setPassword;
    this.getFirstName = getFirstName;
    this.setFirstName = setFirstName;
    this.getLastName = getLastName;
    this.setLastName = setLastName;
    this.getEmail = getEmail;
    this.setEmail = setEmail;
    this.getPhone = getPhone;
    this.setPhone = setPhone;
    this.getRole = getRole;
    this.setRole = setRole;
    this.getDateOfBirth = getDateOfBirth;
    this.setDateOfBirth = setDateOfBirth;

    function getUsername() {
        return self.username;
    }

    function setUsername(username) {
        self.username = username;
    }

    function getPassword() {
        return self.password;
    }

    function setPassword(password) {
        self.password = password;
    }

    function getFirstName() {
        return self.firstName;
    }

    function setFirstName(firstName) {
        self.firstName = firstName;
    }

    function getLastName() {
        return self.lastName;
    }

    function setLastName(lastName) {
        self.lastName = lastName;
    }

    function getEmail() {
        return self.email;
    }

    function setEmail(email) {
        self.email = email;
    }

    function getPhone() {
        return self.phone;
    }

    function setPhone(phone) {
        self.phone = phone;
    }

    function getRole() {
        return self.role;
    }

    function setRole(role) {
        self.role = role;
    }

    function getDateOfBirth() {
        return self.dateOfBirth;
    }

    function setDateOfBirth(dateOfBirth) {
        self.dateOfBirth = dateOfBirth;
    }
}
