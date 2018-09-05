$(function(){
	jQuery.validator.addMethod("notEqualTo", function(value, element, param) {
		return value != $(param).val();
		}, $.validator.format("两次输入不能相同!"));
});