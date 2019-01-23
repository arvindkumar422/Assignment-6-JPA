(function () {
    var $usernameFld, $passwordFld;
    var $removeBtn, $selectBtn, $updateBtn, $createBtn, $findBtn;
    var $firstNameFld, $lastNameFld;
    var $userRowTemplate, $tbody;
    var userService = new AdminUserServiceClient();
    $(main);



    function main() {
        $usernameFld = $("#usernameFld");
        $passwordFld = $("#passwordFld");
        $removeBtn = $("#removeBtn");
        $selectBtn = $("#selectBtn");
        $updateBtn = $("#updateBtn");
        $createBtn = $("#createBtn");
        $findBtn = $("#searchBtn");
        $firstNameFld = $("#firstNameFld");
        $lastNameFld = $("#lastNameFld");

        $userRowTemplate = $(".wbdv-template.wbdv-user");
        $tbody = $(".wbdv-tbody");

        $createBtn.click(createUser);
        $findBtn.click(findAllUsers);

        var promise = userService.findAllUsers();
        promise.then(renderUsers);

    }
    function createUser() {

        var usernameStr = $usernameFld.val();
        var pwd = $passwordFld.val();
        var firstName = $firstNameFld.val();
        var lastName = $lastNameFld.val();
        var uid = new Date().getTime().toString();

        var userObj = {
            id: uid,
            username: usernameStr,
            firstName: firstName,
            lastName: lastName,
        };

        $usernameFld.val("");

        //userService.createUser(JSON.stringify(userObj));

        userService.createUser(userObj).then(function () {
            userService
                .findUserById(uid)
                .then(renderUser);
        });

        // var newUser = $userRowTemplate.clone();
        // newUser.removeClass("wbdv-hidden");
        // newUser.find(".wbdv-username").html(username);
        // newUser.attr("id", timestamp);
        //
        // $tbody.append(newUser);

        //newUser.find(".wbdv-remove").click(deleteUser);
    }
    function findAllUsers() {
        var usernameStr = $usernameFld.val();
        var firstNameStr = $firstNameFld.val();
        var lastNameStr = $lastNameFld.val();

        userService.findUsersByField(usernameStr, firstNameStr, lastNameStr).then(renderUsers);
    }


    function findUserById(uid) {
        var username = $usernameFld.val();
        var promise2 = userService.findUserById(uid);
        promise2.then(renderUser);
    }


    function deleteUser(event) {
        var button = $(event.currentTarget);
        var parent = button.parents(".wbdv-template");
        var id = parent.attr("id");
        //var parent = button.parents(".wbdv-template");
        // parent.remove();
        userService
            .deleteUser(id)
            .then(function () {
                userService
                    .findAllUsers()
                    .then(renderUsers);
            });
    }
    function selectUser() {  }
    function updateUser() {  }
    function renderUser(user) {
        console.log(user.id);
        var clone = $userRowTemplate.clone();
        clone.find(".wbdv-username").html(user.username);
        clone.removeClass("wbdv-hidden");
        clone.attr("id", user.id);
        clone.find(".wbdv-remove").click(deleteUser);
        $tbody.append(clone);
    }
    function renderUsers(users) {

        for(var i = 0; i < users.length; i++) {
            console.log(users);
            var clone = $userRowTemplate.clone();
            clone.find(".wbdv-username").html(users[i].username);
            clone.attr("id", users[i].id);
            clone.removeClass("wbdv-hidden");
            clone.find(".wbdv-remove").click(deleteUser);
            $tbody.append(clone);

        }
    }
})();
