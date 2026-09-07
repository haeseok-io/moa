<template>
    <div class="w-full">
        <label
            v-if="label"
            :for="id"
            class="text-gray-400"
        >
            {{ label }}
        </label>
        <div class="relative flex items-center mt-1 pb-[20px]">
            <input
                :id="id"
                :type="type"
                :value="modelValue"
                :placeholder="placeholder"
                :disabled="disabled"
                :readonly="readonly"
                :maxlength="maxlength"
                @input="updateValue"
                class="w-full h-[40px] border border-gray-200 px-2 outline-none"
            >
            <slot name="append" />
            <p v-if="error" class="absolute bottom-0 text-sm text-red-500">{{ error }}</p>
        </div>
    </div>
</template>

<script setup>
    defineProps({
        modelValue: {
            type: String,
            default: ''
        },
        id: String,
        type: {
            type: String,
            default: 'text'
        },
        label: String,
        placeholder: String,
        disabled: Boolean,
        readonly: Boolean,
        maxlength: Number,
        error: String
    })

    const emit = defineEmits(['update:modelValue'])
    const updateValue = event => {
        emit('update:modelValue', event.target.value)
    }
</script>