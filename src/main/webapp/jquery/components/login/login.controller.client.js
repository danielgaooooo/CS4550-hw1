(function () {
    var $usernameFld;
    var $passwordFld;
    var $loginBtn;

    var userService = new UserServiceClient();
    $(main);

    function main() {
        $usernameFld = $('#username');
        $passwordFld = $('#password');

        $loginBtn = $('#loginBtn').click(login);
    }

    function login() {
        var user = new User();
        user.setUsername($usernameFld.val());
        user.setPassword($passwordFld.val());
        userService.login(user);
    }
})();
