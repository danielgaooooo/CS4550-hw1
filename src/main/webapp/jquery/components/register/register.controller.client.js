(function () {
    var $usernameFld;
    var $passwordFld;
    var $verifyPasswordFld;
    var $registerBtn;
    var userService = new UserServiceClient();
    $(main);

    function main() {
        $('#updateBtn').click(register);
    }

    function register() {
        $usernameFld = $('#username').val();
        $verifyPasswordFld = $('#verifyPassword').val();
        $passwordFld = $('#password').val();
        if ($verifyPasswordFld === $passwordFld) {
            var user = new User();
            user.setUsername($usernameFld);
            user.setPassword($passwordFld);
            userService.register(user);
            emptyFields();
        } else {
            badPassword();
        }
    }

    function emptyFields() {
        $('#username').val('');
        $('#password').val('');
        $('#verifyPassword').val('');
    }
})();
