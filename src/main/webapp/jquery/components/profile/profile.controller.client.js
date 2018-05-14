(function() {
    $(init);

    var $username;
    var $password;
    var $firstName;
    var $lastName;
    var $email;
    var $phone;
    var $role;
    var $date;

    var $updateBtn;

    var userService = new UserServiceClient();

    function init() {
        $username = $("#username");
        $password = $("#inputPassword");
        $firstName = $("#firstName");
        $lastName = $("#lastName");
        $email = $("#email");
        $phone = $("#phone");
        $role = $("#roleFld");
        $date = $("#date");

        $updateBtn = $("#updateBtn")
            .click(updateUser);
        findUserById(342);
    }

    function updateUser() {
        var user = new User();
        user.setUsername($username.val());
        user.setPassword($password.val());
        user.setFirstName($firstName.val());
        user.setLastName($lastName.val());
        user.setEmail($email.val());
        user.setPhone($phone.val());
        user.setRole($role.val());
        user.setDateOfBirth($date.val());

        userService.updateUser(342, user).then(renderUser);
    }

    function findUserById(userId) {
        userService
            .findUserById(userId)
            .then(renderUser);
    }

    function renderUser(user) {
        $username.val(user.username);
        $password.val(user.password);
        $firstName.val(user.firstName);
        $lastName.val(user.lastName);
        $email.val(user.email);
        $phone.val(user.phone);
        $role.val(user.role);
        $date.val(user.dateOfBirth.substr(0, 10));
    }
})();