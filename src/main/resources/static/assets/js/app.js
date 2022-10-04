class App {
    static SweetAlert = class {
        static showSuccessAlert(t) {
            Swal.fire({
                icon: 'success',
                title: t,
                position: 'top-end',
                showConfirmButton: false,
                timer: 1500
            })
        }
    }

    static showErrorAlert(t) {
        Swal.fire({
            icon: 'error',
            title: 'Warning',
            position: 'top-end',
            text: t,
            timer: 1500
        })
    }

    static showRemoveConfirm() {
        return Swal.fire({
            title: 'Are you sure to deactive the selected customer ?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085D6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, deactive it!',
        });
    }

    static IziToast = class {
        static showErrorAlert(m) {
            iziToast.error({
                title: 'Error',
                position: 'topRight',
                message: m,
            })
        }

        static showSuccessAlert(m) {
            iziToast.success({
                title: 'Success',
                position: 'topRight',
                message: m,
            })
        }
    }
}

class User {
    constructor(id, imageUrl, username, password, fullName, phone, regionLocation, role) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.username= username;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.regionLocation = regionLocation;
        this.role = role;
    }
}

class Product {
    constructor(id, imageUrl, title, manufacturer, price, quantity) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.title = title;
        this.manufacturer = manufacturer;
        this.price = price;
        this.quantity = quantity;
    }
}

class RegionLocation {
    constructor(id, provinceId, provinceName, districtId, districtName, wardId, wardName, address) {
        this.id = id;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.districtId = districtId;
        this.districtName = districtName;
        this.wardId = wardId;
        this.wardName = wardName;
        this.address = address;
    }
}

class Role {
    constructor(id, roleName) {
        this.id = id;
        this.roleName = roleName;
    }
}

class Manufacturer {
    constructor(id, manufacturerName) {
        this.id = id;
        this.manufacturerName = manufacturerName;
    }
}

