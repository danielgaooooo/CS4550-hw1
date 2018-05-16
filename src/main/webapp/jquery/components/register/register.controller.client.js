(function () {
    var $usernameFld;
    var $passwordFld;
    var $verifyPasswordFld;
    var userService = new UserServiceClient();
    $(main);

    function main() {
        $('#registerBtn').click(register);
    }

    function register() {
        $usernameFld = $('#username').val();
        $verifyPasswordFld = $('#verifyPassword').val();
        $passwordFld = $('#password').val();
        if ($usernameFld === "" || $verifyPasswordFld === "" || $passwordFld === "") {
            alert('Please fill out all fields before attempting to register.');
        } else if ($verifyPasswordFld !== $passwordFld) {
            alert('Your passwords do not match.')
        } else {
            userService.findUserByUsername($usernameFld).then(verifyValidUsername);
        }
        emptyFields();
    }

    function verifyValidUsername(username) {
        if (username.username == null) {
            var user = new User();
            user.setUsername($usernameFld);
            user.setPassword($passwordFld);
            userService.register(user).then(goToProfile);
        } else {
            alert('That username has been taken. Please try another.');
        }
    }

    function goToProfile(user) {
        window.location.href = '../profile/profile.template.client.html?userId=' + user.id;
    }

    function emptyFields() {
        $('#username').val('');
        $('#password').val('');
        $('#verifyPassword').val('');
    }
})();
