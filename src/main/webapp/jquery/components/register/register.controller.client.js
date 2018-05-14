(function () {
    var $usernameFld;
    var $passwordFld;
    var $verifyPasswordFld;
    var $registerBtn;
    var userService = new UserService();
    $(main);

    function main() {
        $usernameFld = $('#username').val();
        $passwordFld = $('#password').val();
        $verifyPasswordFld = $('#verifyPassword').val();
        var user = {
            username: $usernameFld,
            password: $passwordFld,
        };
        $('#updateBtn').click(register(user));
    }

    function register(user) {
        userService.createUser(user);
    }
})();
