function Product(opts) {
    if (!opts) opts = {};
    this.id = opts.id || '';
    this.name = opts.name || '';
    this.productCode = opts.productCode || '';
    this.unitPrice = opts.unitPrice || '';
}

module.exports = Product;