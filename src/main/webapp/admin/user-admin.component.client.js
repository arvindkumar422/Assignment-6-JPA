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
        $findBtn.click(findUserById);

        var promise = userService.findAllUsers();
        promise.then(renderUsers);

    }
    function createUser() {

        var username = $usernameFld.val();
        var timestamp = new Date().getTime();

        $usernameFld.val("");

        var promise_create = userService.createUser(username);
        promise_create.then(renderUsers);

        // var newUser = $userRowTemplate.clone();
        // newUser.removeClass("wbdv-hidden");
        // newUser.find(".wbdv-username").html(username);
        // newUser.attr("id", timestamp);
        //
        // $tbody.append(newUser);

        //newUser.find(".wbdv-remove").click(deleteUser);
    }
    function findAllUsers() {  }
    function findUserById() {
        var username = $usernameFld.val();
        var promise2 = userService.findUserById(username);
        promise2.then(renderUser);
    }
    function deleteUser(event) {
        var button = $(event.currentTarget);
        var parent = button.parents(".wbdv-template");
        parent.remove();
    }
    function selectUser() {  }
    function updateUser() {  }
    function renderUser(user) {
        console.log(user);
    }
    function renderUsers(users) {

        for(var i = 0; i < users.length; i++) {
            console.log(users);
            var clone = $userRowTemplate.clone();
            clone.find(".wbdv-username").html(users[i].username);
            clone.removeClass("wbdv-hidden");
            $tbody.append(clone);
        }
    }
})();
