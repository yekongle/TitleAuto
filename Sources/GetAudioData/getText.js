/*
*在带有itme_list类的标签中下的子元素前添加一个button和textarea，用来获取数据
* */


//$(document).ready(function(){
	//alert("helloworld");
	//$(".copy").append("<div>this is a test</div>");
	$('.itme_list').prepend('<li><div class="container"><button id="getAudioData">获取数据</button>&nbsp;' +
    '<textarea id="outputAudioData"></textarea></div></li>');
 
//  为button绑定点击事件，遍历相关元素获取数据
 
  $('#getAudioData').click(function(){
    if($('#thelist .keywork p').length > 0){
        alert('请先等待所有转写完成');
    }else{
        var data = {};
        $('#thelist .item').each(function(i,e){   //i是索引,e相当于this，当前对象本身
            //data[$('.file-info p.info',e).text()] = $(e).next().text();
            data[i]=$(e).next().text();
        });
        $('#outputAudioData').text(JSON.stringify(data,null,4));
        alert("数据已经生成");
    }
});  
//});
