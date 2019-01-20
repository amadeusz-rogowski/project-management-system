const validateCategory = () => {

    const categoryName = document.forms['categoryForm']['categoryName'].value

    if (categoryName === null || categoryName === '') {
        alert('Category name is required!')
        return false
    }

    return true
}

const validateTask = () => {

    const title = document.forms['taskForm']['title'].value
    const description = document.forms['taskForm']['description'].value

    if (title === null || title.length < 3 || title.length > 15) {
        alert('Title length should be between 3 and 15')
        return false
    }

    if (description === null || description.length < 3 || description.length > 48) {
        alert('Description length should be between 3 and 48 characters')
        return false
    }

    return true
}