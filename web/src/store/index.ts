import { configureStore } from '@reduxjs/toolkit';
import { useDispatch } from 'react-redux';

import { isDevelopment } from 'utils/env';

import rootReducer from './rootReducer';

const createStore = () => configureStore({
  reducer: rootReducer,
  devTools: isDevelopment(),
});

const store = createStore();

type AppDispatch = typeof store.dispatch;

// Export a hook that can be reused to resolve types
export const useAppDispatch = () => useDispatch<AppDispatch>();

export default store;
