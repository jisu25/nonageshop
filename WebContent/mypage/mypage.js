function go_cart() {
  if (document.formm.quantity.value == "") {
    alert("수량을 입력하여 주세요.");
    document.formm.quantity.focus();
  } else {
    document.formm.action = "cartInsert.do";
    document.formm.submit();
  }
}

function go_cart_delete() {
  var count = 0;

  if (document.formm.no.length == undefined) {
    if (document.formm.no.checked == true) {
      count++;
    }
  }

  for ( var i = 0; i < document.formm.no.length; i++) {
    alert("" + document.formm.no[i].checked);
    if (document.formm.no[i].checked == true) {
      count++;
      alert("" + count);
    }
  }
  if (count == 0) {
    alert("삭제할 항목을 선택해 주세요.");

  } else {
    document.formm.action = "cartDelete.do";
    document.formm.submit();
  }
}

function go_order_insert() {
  document.formm.action = "orderInsert.do";
  document.formm.submit();
}

function go_order_delete() {
  var count = 0;

  if (document.formm.no.length == undefined) {
    if (document.formm.no.checked == true) {
      count++;
    }
  }

  for ( var i = 0; i < document.formm.no.length; i++) {
    if (document.formm.no[i].checked == true) {
      count++;
    }
  }
  if (count == 0) {
    alert("삭제할 항목을 선택해 주세요.");

  } else {
    document.formm.action = "orderDelete.do";
    document.formm.submit();
  }
}

function go_order() {
  document.formm.action = "mypage.do";
  document.formm.submit();
}