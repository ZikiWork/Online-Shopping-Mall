function add(goodsId, goodsNum) {
	var g1 = {
		"goodsId" : goodsId,
		"goodsNum" : goodsNum
	};
	if ($.cookie("shopcar") == null) {
		var d=[];
		d.push(g1);
		$.cookie("shopcar", JSON.stringify(d));
	}else{
		var d=get();
		addGoods(d,g1);
		$.cookie("shopcar", JSON.stringify(d));
	}
}

function getGoodsNumById(goodses,goodsId){
	for(var i=0;i<goodses.length;i++){
		if(goodsId==goodses[i].goodsId){
			return goodses[i].goodsNum;
		}
	}
}

function del(goodsId) {
	var d=get();
	for(var i=0;i<d.length;i++){
		if(goodsId==d[i].goodsId){
			d.splice(i,1);
			return;
		}
	}
}

function update(goodsId, goodsNums) {

}

function get() {
	var goodses = $.cookie("shopcar");
	if(goodses==null){
		return [];
	}
	//alert(goodses);
	return JSON.parse(goodses);
}

/**
 * 增加商品到Cookie中
 * @param goodses
 * @param goods
 */
function addGoods(goodses,goods){
	for(var i=0;i<goodses.length;i++){
		if(goods.goodsId==goodses[i].goodsId){
			goodses[i].goodsNum=parseInt(goodses[i].goodsNum)+parseInt(goods.goodsNum);
			return;
		}
	}
	goodses.push(goods);
}

/**
 * 往第一个JSON中加入第二个JSON
 * @param target
 * @param src
 */
function addJsons(target, src) {
	for (var p in src) {
		target[p] = src[p];
	}
}
