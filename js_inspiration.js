function recursiveprimes(upperbound, pipe=[], val=2) {
	if (val > upperbound) {
		return pipe.map(x =>x());
	}
	 return pipe.reduce((accum, val) => val(accum), val) ? recursiveprimes(upperbound, pipe.concat(buildGate(val)), val+1)
	 : recursiveprimes(upperbound, pipe, val+1)
}

function buildGate(val) {
	return function(x) {
		if (x === undefined) {
			return val;
		}
		return x % val === 0 ? false : x;
	};
}

console.log(recursiveprimes(50))