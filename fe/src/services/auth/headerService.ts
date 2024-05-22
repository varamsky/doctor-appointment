export const setAuthorizationHeader = (config: any) => {
  const jwtLocalStorageKey = import.meta.env.VITE_JWT_LOCAL_STORAGE_KEY;

  const token = localStorage.getItem(jwtLocalStorageKey);
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
};
