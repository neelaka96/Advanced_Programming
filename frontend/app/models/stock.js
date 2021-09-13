function Stock(opts) {
    if (!opts) opts = {};
    this.id = opts.id || '';
    this.fromBranchId = opts.fromBranchId || '';
    this.toBranchId = opts.toBranchId || '';
    this.productId = opts.productId || '';
    this.stock_qty = opts.stock_qty || '';
    this.date = opts.date || '';
}

module.exports = Stock;