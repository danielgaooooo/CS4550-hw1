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
        userService.login(user).then(checkExists);
    }

    function checkExists(user) {
        if (user.username == null && user.password == null) {
            alert('Your username or password is incorrect. Please try again.');
        } else {
            goToProfile(user);
        }
    }

    function goToProfile(user) {
        window.location.href = '../profile/profile.template.client.html?userId=' + user.id;
    }
})();
