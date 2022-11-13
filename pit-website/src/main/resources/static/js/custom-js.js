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

$("#ServiceMeldingButton").click(function () {
  $('#service-form').show();
  $('#priority-form').hide();
  $('#incident-form').hide();
});

$("#IncidentMeldingButton").click(function () {
  $('#incident-form').show();
  $('#priority-form').hide();
  $('#service-form').hide();

});

$("#SpoedMeldingButton").click(function () {
  $('#priority-form').show();
  $('#incident-form').hide();
  $('#service-form').hide();
});

$("#btnRegisterDone").click(function () {
  window.location.replace("/portal/login");
});

$("#btnLogin").click(function () {
  if(isEmail($("#email").val())){
  $('#msgLoginFailed').hide();
  $.post("/authenticate/" + $("#email").val() + "/" + $("#password").val(), function (data, status) {
    if (data == 'AUTHENTICATED') {
      window.location.replace("/portal/dashboard");
    } else if (data == "REQUIRE_TOKEN_CHECK") {
      $("#modalLoginCheckToken").modal('show');
    } else {
      $('#msgLoginFailed').show();
    }
  }).fail(function () {
    $('#msgLoginFailed').show();
  });
  } else {
    $(".pit-noemail-input").show();
  }
});
function isEmail(email) {
  var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
  return regex.test(email);
}
$("#btnRegister").click(function () {
  if(isEmail($("#email").val())){
  $.post("/users/register/" + $("#email").val() + "/" + $("#password").val(), function (data, status) {
    $username = $("#email").val();
    if (status == 'success') {
      $("#tokenQr").attr("src", "https://zxing.org/w/chart?cht=qr&chs=250x250&chld=M&choe=UTF-8&chl=otpauth://totp/Precise-IT:" + $username + "?secret=" + data + "&issuer=" + $username);
      $("#tokenValue").text(data);
      $("#modalRegister").modal('show');
    }
  });
  } else {
    $(".pit-noemail-input").show();
  }
});

$("#btnCreateTicket").click(function () {
    $.post("/tickets/create-ticket/" + $("#probleemBeschrijving").val() + "/" + $("#uwId").val(), function (data, status) {
      if (status == 'success') {
        console.log("success");
      }
      console.log("status fail: " + status)
    });
  console.log("status fail: ")
});

$("#btnTokenVerify").click(function () {
  $('#msgTokenCheckFailed').hide();
  $.post("/authenticate/token/" + $("#email").val() + "/" + $("#password").val() + "/" + $("#loginToken").val(), function (data, status) {
    if (data == 'AUTHENTICATED') {
      window.location.replace("/portal/dashboard");
    } else {
      $('#msgTokenCheckFailed').show();
    }
  }).fail(function () {
    $('#msgTokenCheckFailed').show();
  });
});

$("#btnLogout,#btnLogoutHeader").click(function () {
  $.post("/authenticate/logout", function (data, status) {
    window.location.replace("/portal/logout-succes")
  });
});