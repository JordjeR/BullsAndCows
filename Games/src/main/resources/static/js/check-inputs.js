function check() {
    let btn = document.getElementById("submit");

    $('body').on('input', '#attempts', function () {
        if (this.value.length === 4) {
            btn.removeAttribute('disabled');
        } else {
            btn.setAttribute('disabled', true);
        }
        this.value = this.value.replace(/[^0-9]/g, '');
    })
}