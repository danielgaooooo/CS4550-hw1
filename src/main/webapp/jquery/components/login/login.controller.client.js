(function () {
    var $usernameFld;
    var $passwordFld;
    var $loginBtn;
    var $forgotPasswordBtn;
    var $registerBtn;

    var userService = new UserServiceClient();
    $(main);

    function main() {
        $usernameFld = $('#username').val();
        $passwordFld = $('#password').val();

        $loginBtn = $('#loginBtn').click(login);
        $forgotPasswordBtn = $('#forgotPassword').click(forgotPassword);
        $registerBtn = $('#signUp').click(register);
    }

    function login() {
        var user = new User();
        user.setUsername($usernameFld);
        user.setPassword($passwordFld);
        userService.login(user);
    }

    function forgotPassword() {

    }

    function register() {

    }
})();
