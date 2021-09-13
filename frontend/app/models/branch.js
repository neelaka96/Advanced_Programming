function Branch(opts) {
  if (!opts) opts = {};
  this.id = opts.id || '';
  this.name = opts.name || '';
  this.location = opts.location || '';
  this.contact = opts.contact || '';
}

module.exports = Branch;