(function () {
    var $usernameFld, $passwordFld;
    var $removeBtn, $selectBtn, $updateBtn, $createBtn, $findBtn;
    var $firstNameFld, $lastNameFld, $roleFld;
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
        $roleFld = $("#roleFld");

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
        var roletype = $roleFld.val();
        var uid = new Date().getTime().toString();

        if (validateForm()) {

            var userObj = {
                id: uid,
                username: usernameStr,
                password: pwd,
                firstName: firstName,
                lastName: lastName,
                role: roletype
            };

            userService.createUser(userObj).then(function () {
                userService
                    .findUserById(uid)
                    .then(renderUser);
            });
        }
    }

    function findAllUsers() {
        var usernameStr = $usernameFld.val();
        var firstNameStr = $firstNameFld.val();
        var lastNameStr = $lastNameFld.val();
        var roletype = $roleFld.val();

        userService.findUsersByField(usernameStr, firstNameStr, lastNameStr, roletype)
            .then(renderUsers);
    }

    function findUserById(uid) {
        var username = $usernameFld.val();
        userService.findUserById(uid)
            .then(function () {
                userService
                    .findUserById(uid)
                    .then(updateForm);
            });
        //promise2.then(renderUser);
    }

    function updateForm(user) {
        document.getElementById("usernameFld").value = user.username;
        document.getElementById("passwordFld").value = user.password;
        document.getElementById("firstNameFld").value = user.firstName;
        document.getElementById("lastNameFld").value = user.lastName;
        document.getElementById("roleFld").value = user.role;
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

    function selectUser() {
    }

    function updateUserForm() {

        var button = $(event.currentTarget);
        var parent = button.parents(".wbdv-template");
        var id = parent.attr("id");

        $(".wbdv-update").attr("id", id);

        var usr = findUserById(id);

        console.log(usr);

        var usernameStr = document.getElementById(id).childNodes[1].innerText;
        var pwd = document.getElementById(id).childNodes[3].innerText;
        var firstName = document.getElementById(id).childNodes[5].innerText;
        var lastName = document.getElementById(id).childNodes[7].innerText;
        var roletype = document.getElementById(id).childNodes[9].innerText;

        // var userObj = {
        //     id: id,
        //     username: usernameStr,
        //     password: pwd,
        //     firstName: firstName,
        //     lastName: lastName,
        //     role: roletype
        // };

        const userObj = new User(id, usernameStr, pwd, firstName, lastName, roletype);
    }

    function updateUser() {
        var usernameStr = $usernameFld.val();
        var pwd = $passwordFld.val();
        var firstName = $firstNameFld.val();
        var lastName = $lastNameFld.val();
        var roletype = $roleFld.val();
        var button = $(event.currentTarget);
        var id = button.attr("id");

        var userObj = {
            id: id,
            username: usernameStr,
            password: pwd,
            firstName: firstName,
            lastName: lastName,
            role: roletype
        };

        //const userObj = new User(id, usernameStr, pwd, firstName, lastName, roletype);

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
        clone.find(".wbdv-password").html(user.password);
        clone.find(".wbdv-first-name").html(user.firstName);
        clone.find(".wbdv-last-name").html(user.lastName);
        clone.find(".wbdv-role").html(user.role);
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
        for (var i = 0; i < users.length; i++) {
            var clone = $userRowTemplate.clone();
            clone.find(".wbdv-username").html(users[i].username);
            clone.find(".wbdv-password").html(users[i].password);
            clone.find(".wbdv-first-name").html(users[i].firstName);
            clone.find(".wbdv-last-name").html(users[i].lastName);
            clone.find(".wbdv-role").html(users[i].role);
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

    function validateForm() {
        var username = $usernameFld.val();
        var password = $passwordFld.val();
        var firstname = $firstNameFld.val();

        if (username.length == 0) {
            alert("Please enter Username value.");
            return false;
        }
        if (password.length == 0) {
            alert("Please enter Password value.");
            return false;
        }
        if (firstname.length == 0) {
            alert("Please enter First Name value.");
            return false;
        }
        return true;
    }
})();
