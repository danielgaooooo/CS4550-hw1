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
        var user = new User();
        user.setUsername($usernameFld);
        user.setPassword($passwordFld);
        userService.register(user);
        emptyFields();
    }

    function emptyFields() {
        $('#username').val('');
        $('#password').val('');
        $('#verifyPassword').val('');
    }
})();
