function User(opts) {
  if (!opts) opts = {};
  this.id = opts.id || '';
  this.nic = opts.nic || '';
  this.name = opts.name || '';
  this.userType = opts.userType || '';
  this.address = opts.address || '';
  this.contact = opts.contact || '';
  this.branchId = opts.branchId || '';
  this.userName = opts.userName || '';
  this.password = opts.password || '';
}

module.exports = User;
