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
        $verifyPasswordFld = $('#verifyPassword').val();
        $usernameFld = $('#username').val();
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
