$(document).ready(function(){
  $(".mobile-icon").click(function(){
    $(".mobile-nav").toggle();
  });
});

$("#btnCancelRegister").click(function () {
  window.location.replace("/portal/login");
});

$("#btnCancelLogin").click(function () {
  window.location.replace("/");
});

$("#btnRegisterDone").click(function () {
  window.location.replace("/portal/login");
});

$("#btnLogin").click(function () {
  $('#msgLoginFailed').hide();
  $.post("/authenticate/" + $("#username").val() + "/" + $("#password").val(), function (data, status) {
    if (data == 'AUTHENTICATED') {
      window.location.replace("/portal");
    } else if (data == "REQUIRE_TOKEN_CHECK") {
      $("#modalLoginCheckToken").modal('show');
    } else {
      $('#msgLoginFailed').show();
    }
  }).fail(function () {
    $('#msgLoginFailed').show();
  });
});

$("#btnRegister").click(function () {
  $.post("/users/register/" + $("#username").val() + "/" + $("#password").val(), function (data, status) {
    $username = $("#username").val();
    if (status == 'success') {
      $("#tokenQr").attr("src", "https://zxing.org/w/chart?cht=qr&chs=250x250&chld=M&choe=UTF-8&chl=otpauth://totp/Precise-IT:" + $username + "?secret=" + data + "&issuer=" + $username);
      $("#tokenValue").text(data);
      $("#modalRegister").modal('show');
    }
  });
});

$("#btnTokenVerify").click(function () {
  $('#msgTokenCheckFailed').hide();
  $.post("/authenticate/token/" + $("#username").val() + "/" + $("#password").val() + "/" + $("#loginToken").val(), function (data, status) {
    if (data == 'AUTHENTICATED') {
      window.location.replace("/portal");
    } else {
      $('#msgTokenCheckFailed').show();
    }
  }).fail(function () {
    $('#msgTokenCheckFailed').show();
  });
});

$("#btnLogout").click(function () {
  $.post("/authenticate/logout", function (data, status) {
    window.location.replace("/")
  });
});