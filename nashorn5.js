// Sanjana Updated - 02/07/2025
function Product(name) {
    this.name = name;
}

//changed prototype stock and price
Product.prototype.stock = 10;
Product.prototype.price = 70.89;
Product.prototype.getValueOfGoods = function() {
    return this.stock * this.price;
};

//new product is updated
var product = new Product('Bag');
product.sale = 5;
product.price = 21.99;
product.stock = 80;

print('Value of Goods: ' + product.getValueOfGoods());


var getValueOfGoods = function(javaProduct) {
    var jsProduct = new Product();
    Object.bindProperties(jsProduct, javaProduct);
    return jsProduct.getValueOfGoods();
};