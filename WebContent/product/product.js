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
  for ( var i = 0; i < document.formm.cseq.length; i++) {
    if (document.formm.cseq[i].checked == true) {
      count++;
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
  for ( var i = 0; i < document.formm.oseq.length; i++) {
    if (document.formm.oseq[i].checked == true) {
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