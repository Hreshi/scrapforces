const handleInput = document.getElementById('handle')
const handleSubmit = document.getElementById('handle-button')
const ratingList = document.getElementById('list')

handleSubmit.addEventListener("click", function () {
	let url = 'stats?handle='
	url += handleInput.value
	fetch(url).then(function (res) {
		res.json().then(function (data) {
			ratingList.innerHTML = ""
			let allKeys = Object.keys(data)
			for (let key of allKeys) {
				let ele = createElement(key, data[key])
				list.append(ele)
			}
		})
	}).catch (function(err) {
		console.log("Error:" + err.message);
	})
})

function createElement(rating, problems) {
	let text = "Solved " + problems + " problems of rating " + rating
	let li = document.createElement('li')
	li.innerText = text
	return li
}