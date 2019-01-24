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
        $updateBtn.click(updateUser);

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
        userService.findUserById(uid)
            .then(function () {
                userService
                    .findUserById(uid)
                    .then(updateForm);
            });;
        //promise2.then(renderUser);
    }

    function updateForm(user) {
        document.getElementById("usernameFld").value = user.username;
        document.getElementById("firstNameFld").value = user.firstName;
        document.getElementById("lastNameFld").value = user.lastName;
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
    function updateUserForm() {

        var button = $(event.currentTarget);
        var parent = button.parents(".wbdv-template");
        var id = parent.attr("id");

        $(".wbdv-update").attr("id", id);

        var usr = findUserById(id);


        console.log(usr);

        var usernameStr = document.getElementById(id).childNodes[1].innerText;
        var firstName = document.getElementById(id).childNodes[5].innerText;
        var lastName = document.getElementById(id).childNodes[7].innerText;



        var userObj = {
            id: id,
            username: usernameStr,
            firstName: firstName,
            lastName: lastName,
        };

        // console.log(id);
        //
        // var userObj = {
        //     id: id,
        //     username: usernameStr,
        //     firstName: firstName,
        //     lastName: lastName,
        // };
        //
        // userService.updateUser(id, userObj).then(function () {
        //     userService
        //         .findUserById(id)
        //         .then(renderUser);
        // });


    }

    function updateUser() {
        var usernameStr = $usernameFld.val();
        var firstName = $firstNameFld.val();
        var lastName = $lastNameFld.val();
        var button = $(event.currentTarget);
        var id = button.attr("id");


        var userObj = {
            id: id,
            username: usernameStr,
            firstName: firstName,
            lastName: lastName,
        };

        userService.updateUser(id, userObj)
            .then(function () {
            userService
                .findAllUsers()
                .then(renderUsers);
        });
    }


    function renderUser(user) {
        var clone = $userRowTemplate.clone();
        clone.find(".wbdv-username").html(user.username);
        clone.find(".wbdv-first-name").html(user.firstName);
        clone.find(".wbdv-last-name").html(user.lastName);
        clone.removeClass("wbdv-hidden");
        clone.attr("id", user.id);
        clone.find(".wbdv-remove").click(deleteUser);
        clone.find(".wbdv-edit").click(updateUserForm);
        $tbody.append(clone);

        $usernameFld.val("");
        $passwordFld.val("");
        $firstNameFld.val("");
        $lastNameFld.val("");
    }
    function renderUsers(users) {

        $tbody.find("tr:gt(0)").remove();
        for(var i = 0; i < users.length; i++) {
            var clone = $userRowTemplate.clone();
            clone.find(".wbdv-username").html(users[i].username);
            clone.find(".wbdv-first-name").html(users[i].firstName);
            clone.find(".wbdv-last-name").html(users[i].lastName);
            clone.attr("id", users[i].id);
            clone.removeClass("wbdv-hidden");
            clone.find(".wbdv-remove").click(deleteUser);
            clone.find(".wbdv-edit").click(updateUserForm);
            $tbody.append(clone);

            $usernameFld.val("");
            $passwordFld.val("");
            $firstNameFld.val("");
            $lastNameFld.val("");
        }
    }
})();
