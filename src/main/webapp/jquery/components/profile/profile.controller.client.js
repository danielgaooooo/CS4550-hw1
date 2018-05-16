(function () {
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
    var $logoutBtn;

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
        $logoutBtn = $("#logoutBtn")
            .click(logoutUser);
        var id = getUrlVars();
        findUserById(id);
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

        var id = getUrlVars();
        userService.updateUser(id, user).then(renderUser);
        success();
    }

    function logoutUser() {
        window.location.href = '../login/login.template.client.html';
    }

    function success() {
        alert('Profile updated successfully');
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
        if (user.dateOfBirth != null) {
            $date.val(user.dateOfBirth.substr(0, 10));
        }
    }

    function getUrlVars() {
        var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1);
        var id = hashes.substr(7);
        return parseInt(id);
    }
})();